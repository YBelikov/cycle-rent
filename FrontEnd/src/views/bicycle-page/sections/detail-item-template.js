export const getDetailsTemplate =
    ({
         id = '',
         name = '',
         price = 0,
         photo = '',
         description = ''
    }) =>
        `<li class="list-group-item">
            <div class="detail-row media align-items-lg-center flex-column flex-lg-row">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="check${id}" data-price="${price}">
                    <label class="custom-control-label" for="check${id}"></label>
                </div>
                <div class="media-body order-2 order-lg-1">
                    <h5 class="mt-0 font-weight-bold mb-2">${name}</h5>
                    <p class="font-italic text-muted mb-0 small">${description}</p>
                    <div class="d-flex align-items-center justify-content-between mt-1">
                        <h6 class="font-weight-bold my-2">${price} UAH</h6>
                    </div>
                </div>
                <img src="/img/details/${photo}" alt="Generic placeholder image"
                     width="200" class="ml-lg-5 order-1 order-lg-2">
            </div>
        </li>`;
