/**
 * 
 */

// 例：deleteUtils.js などに追加
function setDeleteSize(index) {
  const select = document.querySelector(`select[name='size_${index}']`);
  const hiddenInput = document.getElementById(`delete-size-${index}`);
  if (select && hiddenInput) {
    hiddenInput.value = select.value;
  }
  return true;
}
