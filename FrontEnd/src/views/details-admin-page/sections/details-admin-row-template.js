export const detailsAdminRowTemplate =  ({
                                             id = '',
                                             name = '',
                                             price = 0,
                                             photo = '',
                                             description = ''
                                         }) =>
    `<tr>
         <th scope="row">${name}</th>
         <td class="w-25"><img class="img-fluid img-thumbnail" src="/images/details/${photo}" alt="Sheep" /></td>
         <td>${description}</td>
         <td>${price}</td>
         <td><a href="/detail-editor/${id}"><i class="fas fa-edit fa-lg"></i></a></td>
         <td><a th:href="@{/admin/{bicycleId}/details/remove/${id}(bicycleId=${bicycle.id})}"><i class="fas fa-trash-alt fa-lg"></i></td>
     </tr>`;