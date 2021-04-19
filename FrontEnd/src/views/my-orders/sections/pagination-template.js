export const getPaginationTemplate =
  (totalPages = 1, currentPage = 1) =>
    `<nav>
  <ul class="pagination pagination-lg justify-content-center my-pagination">
    <li class="page-item ${currentPage === 1 ? 'disabled' : ''}">
      <div id="previousButton" class="page-link">Previous</div>
    </li>
    <li class="page-item page-number"> ${currentPage}/${totalPages} </li>
    <li class="page-item ${currentPage === totalPages ? 'disabled' : ''}">
      <div id="nextButton" class="page-link">Next</div>
    </li>
  </ul>
</nav>
`;
