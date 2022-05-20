export const getDetailItemTemplate =
    ({
      id = 0,
      name = '',
      photo = '',
      price = 0
    }, quantity) =>
        `<div class="info-list-item">
          <div class="bicycle-name">
            <img src="images/details/${photo}" alt="${name}"/>
            <span>${name}</span>
          </div>
          <div class="divider"></div>
          <div class="info-list__item-price">
            Price:
            <span class="info-list__item-price__items">${quantity}</span> x
            <span class="info-list__item-price__cost">${price} UAH</span> =
            <span class="info-list__item-price__cost">${price * quantity}</span>
          </div>
        </div>`;