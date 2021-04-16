export const getPlacesTemplate = (
    {
      id = '',
      place = ''
    }) =>
    `<option value="${id}">${place}</option>`;