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
            <th scope="row"><a href="/details-admin/${id}">${name}</a></th>
                <td class="w-25"><img class="img-fluid img-thumbnail" src="/img/bicycles/${photo}" alt="Sheep" /></td>
                <td>${description}</td>
                <td>${price}</td>
                <td>${weight}</td>
                <td>${numOfSpeeds}</td>
                <td>${type}</td>
                <td><i class="fas fa-edit fa-lg"></i></td>
                <td><i class="fas fa-trash-alt fa-lg"></i></td>
        </tr>`;