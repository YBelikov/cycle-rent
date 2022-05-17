export const bicycleAdminRowTemplate =  ({
         id = '',
         name = '',
         type = '',
         weight = 0,
         price = 0,
         numOfSpeeds = 0,
         photo = '',
         description = ''
    }) =>
        `<tr>
            <th scope="row"><a href="/bicycle-detail-admin/${id}">${name}</a></th>
                <td class="w-25"><img class="img-fluid img-thumbnail" src="/images/bicycles/${photo}" alt="Sheep" /></td>
                <td>${description}</td>
                <td>${price}</td>
                <td>${weight}</td>
                <td>${numOfSpeeds}</td>
                <td>${type}</td>
                <td><a href="/bicycle-editor/${id}"><i class="fas fa-edit fa-lg"></i></a></td>
                <td><a href="/admin/bicycle/remove/${id}"><i class="fas fa-trash fa-lg"></i></a></td>
            </td>
        </tr>`;