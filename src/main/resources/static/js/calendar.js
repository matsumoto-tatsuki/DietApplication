'use strict'

// function changeImage(element, index) {
// 	console.log(index);
// 	let resultInput = document.getElementById('result-input-' + index);
// 	let imagePath = element.getAttribute('src');
// 	let isResult = (imagePath === '/images/success.png');
// 	if (isResult) {
// 		element.src = "/images/success.png";
// 	} else {
// 		element.src = "/images/failure.png";
// 	}
// 	resultInput.value = isResult;
// }


function changeImage(element, index) {
	let resultInput = document.getElementById('result-input-' + index);
	let imagePath = element.getAttribute('src');
	let isResult = (imagePath === '/images/success.png');
	
	if (isResult) {
	  element.src = "/images/failure.png";
	} else {
	  element.src = "/images/success.png";
	}
	
	resultInput.value = !isResult;
  }