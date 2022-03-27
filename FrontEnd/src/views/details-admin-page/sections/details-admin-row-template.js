export const detailsAdminRowTemplate =  ({
                                             id = '',
                                             name = '',
                                             price = 0,
                                             photo = '',
                                             description = ''
                                         }) =>
    `<tr>
         <th scope="row">${name}</th>
         <td class="w-25"><img class="img-fluid img-thumbnail" src="/img/details/${photo}" alt="Sheep" /></td>
         <td>${description}</td>
         <td>${price}</td>
         <td><i class="fas fa-edit fa-lg"></i></td>
         <td><i class="fas fa-trash-alt fa-lg"></i></td>
     </tr>`;