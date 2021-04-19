export const getOrderBicycleTemplate =
    ({
        timeStart = '',
        timeEnd = '',
        bicycleDTO = {}
    }, totalTime) =>
        `<div class="info-list-item">
          <a class="bicycle-name" href="/bicycle/${bicycleDTO.id}">
            <img src="img/bicycles/${bicycleDTO.photo}" alt="${bicycleDTO.name}"/>
            <span>${bicycleDTO.name}</span>
          </a>
          <div class="divider"></div>
          <p class="rent-time">Rent time: ${timeStart} - ${timeEnd}</p>
          <div class="info-list__item-price">
            Price:
            <span class="info-list__item-price__items">${totalTime} h</span> x
            <span class="info-list__item-price__cost">${bicycleDTO.price} UAH</span> =
            <span class="info-list__item-price__cost">${totalTime * bicycleDTO.price}</span>
          </div>
        </div>`;