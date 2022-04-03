export const bicycleEditorTemplate =  ({
                                             id = '',
                                             name = '',
                                             type = '',
                                             weight = 0,
                                             price = 0,
                                             numOfSpeeds = 0,
                                             photo = '',
                                             description = ''
                                         }) =>
    `<div class="col-md-4 pl-20 bicycle-edit-area">
        <h2>Item: ${name}</h2>
            <img class="img-fluid py-3" src="/img/bicycles/${photo}"/>
            <form>
                <div class="form-group py-3">
                    <label for="itemPhotoForm">Upload the item photo</label>
                    <input id="itemPhotoForm" class="form-control-file" type="file" /></div>
                <div class="form-group">
                    <label for="itemName">Name</label>
                    <input id="itemName" class="form-control" type="text" value="${name}" placeholder="Enter item name" /></div>
                <div class="bicycle-type-dropdown form-group pt-3">
                    <label for="dropdownType">Type of bicycle<br></label>
                    <select id="dropdownType" class="main-dd-button">
                        <option value="MOUNTAIN">Mountain</option>
                        <option value="CITY">City</option>
                        <option value="FOR_KIDS">For kids</option>
                    </select>
                </div>
                <div class="form-group pt-3">
                    <label for="pricePerHour">Weight</label>
                    <input id="pricePerHour" class="form-control" type="number" value="${weight}" placeholder="Weight" />
                </div>   
                <div class="form-group pt-3">
                    <label for="pricePerHour">Price</label>
                    <input id="pricePerHour" class="form-control" type="number" value="${price}" placeholder="Rent price per hour" />
                </div>  
                <div class="form-group pt-3">
                    <label for="pricePerHour">Number of speeds</label>
                    <input id="pricePerHour" class="form-control" type="number" value="${numOfSpeeds}" placeholder="Number of speeds" />
                </div>       
                <div class="form-group pt-3">
                    <label for="description">Description</label>
                    <textarea id="description" class="form-control" rows="4">${description}</textarea>
                </div>  
                <button class="btn btn-success save-button mt-3" type="submit">Save <em class="fas fa-save"></em></button>
            </form>
    </div>`;