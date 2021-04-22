export const getBicycleTemplate = (
    orderBicycleId,
    timeStart,
    timeEnd,
    totalTime,
    {
      id = '',
      name = '',
      price = 0,
      photo = ''
    }) =>
    `<tr data-product-type="bicycle">
        <th scope="row" class="border-0">
            <div class="p-2">
                <img src="/img/bicycles/${photo}" alt="${name}" width="70" class="img-fluid rounded shadow-sm">
                <div class="ml-3 d-inline-block align-middle">
                    <h5 class="mb-0">
                      <a href="/bicycle/${id}" class="text-dark d-inline-block align-middle">${name}</a>
                    </h5>
                    <span class="text-muted font-weight-normal font-italic">Rent time: ${timeStart} - ${timeEnd}</span>
                </div>
            </div>
        </th>
        <td class="border-0 align-middle"><strong>${totalTime}h * ${price} = ${totalTime
    * price} UAH</strong></td>
        <td class="align-middle"></td>
        <td class="border-0 align-middle" style="text-align: center;">
          <i class="fa fa-trash text-dark btn-trash" data-action="/orderBicycle/delete/${orderBicycleId}"></i>
        </td>
    </tr>`;