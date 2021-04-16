import {getDetailsTemplate} from './sections/details-template';
import {getBicycleTemplate} from "./sections/bicycles-template";

export function fillOrderBicycles(bicycles) {
  let bicyclesHtml = '';
  bicycles.forEach(bicycle => {
    let totalTime = timeNumber(bicycle.timeEnd) - timeNumber(bicycle.timeStart)
    bicyclesHtml += getBicycleTemplate(bicycle.id, bicycle.timeStart,
        bicycle.timeEnd, totalTime, bicycle.bicycleDTO)
  })
  document.getElementById('fillCart').innerHTML = bicyclesHtml;
}

export function fillOrderDetails(details) {
  let det = groupBy(details, "id")
  let values = Object.values(det)

  let detailsHtml = '';
  values.forEach(value => {
    detailsHtml += getDetailsTemplate(value[0], value.length)
  })

  document.getElementById('fillCart').innerHTML += detailsHtml;
}

function groupBy(xs, key) {
  return xs.reduce(function (rv, x) {
    (rv[x[key]] = rv[x[key]] || []).push(x);
    return rv;
  }, {});
}

function timeNumber(time) {
  let timeNumber = Number(time.substring(0, 2))
  if (time.substring(3, 5) === "30") {
    timeNumber += 0.5
  }
  return timeNumber
}

