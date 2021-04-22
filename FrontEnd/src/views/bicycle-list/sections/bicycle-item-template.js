export const getBicycleTemplate =
    ({
      id = '',
      name = '',
      type = '',
      weight = 0,
      price = 0,
      numOfSpeeds = 0,
      photo = '',
      description = ''
    }) =>
        `<div class="col mb-4">
        <div class="card h-100 border-my">
          <a href="bicycle/${id}" class="h-100">
            <img src="img/bicycles/${photo}" class="card-img-top" alt="${photo}">
            <div class="card-body">
              <h5 class="card-title">${name}</h5>
              <h6 class="card-title card-price">${price} UAH/per hour</h6>
              <p class="card-text">${description}</p>
              <ul class="bicycle-info-list">
                <li><em class="fas fa-check"></em> ${type}</li>
                <li><em class="fas fa-check"></em> ${weight} kg</li>
                <li><em class="fas fa-check"></em> ${numOfSpeeds} speed(s)</li>
              </ul>
            </div>
          </a>
        </div>
      </div>`;
