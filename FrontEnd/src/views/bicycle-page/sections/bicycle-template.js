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
        `<div id="bicycleContainer" class="bicycle-info container" style="margin-top: 30px;" data-price="${price}">
            <div id="bicycleAdded" class="alert alert-success" role="alert" style="display: none;">
                Bicycle and details are sucessfully aded to basket!
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="bicycle-image-wrapper"><img class="bicycle-image w-100 d-block"
                                                            src="/img/bicycles/${photo}"
                                                            alt="${name}"></div>
                </div>
                <div class="col-md-6 text-left">
                    <div class="bicycle-name"><h3>${name}</h3></div>
                    ${description}
                    <p></p>
                    <p><strong>Minimum rent time 30 minutes</strong></p>
                    <p><strong>For overusing limit you have to pay 20$/hour</strong></p>
                    <h5>Bike characteristics: </h5>
                    <ul class="list-group">
                        <li class="list-group-item"><p><strong>Weight: </strong>${weight} kg</p></li>
                        <li class="list-group-item"><p><strong>Type: </strong>${type}</p></li>
                        <li class="list-group-item"><p><strong>Number of speeds: </strong>${numOfSpeeds}</p></li>
                        <li class="list-group-item"><p class="price"><strong>Price: ${price} UAH/hour</strong></p></li>
                    </ul>
                </div>
            </div>
         </div>`;
