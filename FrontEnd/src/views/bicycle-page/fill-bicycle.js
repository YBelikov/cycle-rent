import {getBicycleTemplate} from './sections/bicycle-template';
import {getDetailsTemplate} from './sections/detail-item-template';

export function fillBicycle(bicycle) {
  document.getElementById('bicycle-info').innerHTML = getBicycleTemplate(
      bicycle);
}

export function fillDetails(details) {
  let detailsItems = '';
  (details || []).forEach(detail => {
    detailsItems += getDetailsTemplate(detail)
  });
  document.getElementById('details-list').innerHTML = detailsItems;
}

