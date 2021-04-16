export const getDetailsTemplate = (
    {
      id = '',
      name = '',
      price = 0,
      photo = ''
    }, numberOfItems) =>
    `<tr data-product-type="details">
        <th scope="row" class="border-0">
            <div class="p-2">
                <img src="/img/details/${photo}" alt="${name}" width="70" class="img-fluid rounded shadow-sm">
                <div class="ml-3 d-inline-block align-middle">
                    <h5 class="mb-0"><h5 class="text-dark d-inline-block align-middle">${name}</h5>
                    </h5>
                </div>
            </div>
        </th>
        <td class="border-0 align-middle">
          <strong>${price} * ${numberOfItems} = ${numberOfItems * price} UAH</strong>
        </td>
        <td class="align-middle quantity">
          <button data-action="/detail/remove/${id}" class="btn btn-outline-dark">—</button>
          <h5>${numberOfItems}</h5>
          <button data-action="/detail/add/${id}" class="btn btn-outline-dark">+</button>
        </td>
        <td class="border-0 align-middle" style="text-align: center;">
          <i class="fa fa-trash text-dark btn-trash" data-action="/detail/all/delete/${id}"></i>
        </td>
    </tr>`;