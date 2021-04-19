export const getOrderTemplate =
    ({
        id = 0,
        placeDTO = ''
    }, totalPrice) =>
        `<div>
      <div class="row">
        <div class="col col-12 mb-5">
          <div class="order-item">
            <div class="info">
              <div class="right-block">
                <div class="price">
                  <span>${totalPrice}</span>
                  UAH
                </div>
              </div>
              <div>
                <a data-toggle="collapse" href="#collapseOrders-${id}" aria-expanded="false">
                  <span class="order-bicycle-items badge badge-info"># ${id}</span><em
                  class="double-arrow fas fa-angle-double-right"></em>
                </a>
              </div>
              <div>${placeDTO.place}</div>
            </div>
            <div class="info-list collapse" id="collapseOrders-${id}"></div>
          </div>
        </div>
      </div>
    </div>`;