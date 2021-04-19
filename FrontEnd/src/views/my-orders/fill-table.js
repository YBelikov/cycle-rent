import {getOrderTemplate} from "./sections/order-item-template";
import {getPaginationTemplate} from './sections/pagination-template';
import {getOrderBicycleTemplate} from "./sections/order-bicycle-item-template";
import {getDetailItemTemplate} from "./sections/detail-item-template";
import {navActions} from './my-orders';

export function fillTable(orders, orderBicycleMap, priceMap) {
  let orderItems = '';

  (orders || []).forEach(order => {
    let orderId = order.id

    orderItems = getOrderTemplate(order, priceMap[orderId])
    document.getElementById('forOrders').innerHTML += orderItems

    fillOrderBicyclesAndDetails(orderBicycleMap[orderId], order.detailDTOS,
        orderId)
  })
}

export function fillPagination(totalPages, currentPage) {
  let paginationItems = getPaginationTemplate(totalPages, currentPage);
  const pagination = document.getElementById('paginationId');
  pagination.innerHTML = paginationItems;
  navActions();
}

function fillOrderBicyclesAndDetails(orderBicycles, detailDTOS, orderId) {
  let item = '';

  (orderBicycles || []).forEach(orderBicycle => {
    item += getOrderBicycleTemplate(orderBicycle,
        timeNumber(orderBicycle.timeEnd) - timeNumber(orderBicycle.timeStart))
  });

  let values = Object.values(groupBy(detailDTOS, "id"))

  values.forEach(value => {
    item += getDetailItemTemplate(value[0], value.length)
  });

  document.getElementById('collapseOrders-' + orderId).innerHTML = item
}

function timeNumber(time) {
  let tNumber = Number(time.substring(0, 2))
  if (time.substring(3, 5) === "30") {
    tNumber += 0.5
  }
  return tNumber
}

function groupBy(xs, key) {
  return xs.reduce(function (rv, x) {
    (rv[x[key]] = rv[x[key]] || []).push(x);
    return rv;
  }, {});
}