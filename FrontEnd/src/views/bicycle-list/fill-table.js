import {getBicycleTemplate} from './sections/bicycle-item-template';
import {getPaginationTemplate} from './sections/pagination-template';
import {navActions} from './bicycle-list';

export function fillTable(bicycles) {
  let bicyclesItems = '';

  (bicycles || []).forEach(bicycle => {
    bicyclesItems += getBicycleTemplate(bicycle)
  })
  const bicyclesList = document.getElementById('bicyclesList');
  bicyclesList.innerHTML = bicyclesItems;
}

export function fillPagination(totalPages, currentPage) {
  let paginationItems = getPaginationTemplate(totalPages, currentPage);
  const pagination = document.getElementById('paginationId');
  pagination.innerHTML = paginationItems;
  navActions();
}
