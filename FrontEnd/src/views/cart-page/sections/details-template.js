export const getDetailsTemplate = (
    {
      id = '',
      name = '',
      price = 0,
      photo = ''
    }, numberOfItems) =>
    `<tr>
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
        <td class="align-middle">
          <input type="number" class="form-control quantity-input" value="${numberOfItems}" style="width: 80px"/>
        </td>
        <td class="border-0 align-middle" style="text-align: center;"><a href="#" class="text-dark">
        <i class="fa fa-trash"></i></a></td>
    </tr>`;