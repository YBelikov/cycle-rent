import {getBicycleTemplate} from './sections/bicycle-item-template';
import {getPaginationTemplate} from './sections/pagination-template';
import {navActions} from './bicycle-list';

export function fillTable(bicycles) {
  let bicyclesItems = '';
  // перебераем полученный список велосипедов из JSON, вставляем данные в шалбончик
  (bicycles || []).forEach(bicycle => {
    bicyclesItems += getBicycleTemplate(bicycle)
  })
  // находим на странице div для вставки в него велосипедов
  const bicyclesList = document.getElementById('bicyclesList');
  // вставляем все велисипеды в div
  bicyclesList.innerHTML = bicyclesItems;
}

export function fillPagination(totalPages, currentPage) {
  let paginationItems = getPaginationTemplate(totalPages, currentPage);
  const pagination = document.getElementById('paginationId');
  pagination.innerHTML = paginationItems;
  navActions();
}
