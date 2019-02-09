var clicked = false;

function changeColor(id, answerA, answerB, answerC, clickNext) {

	if (clicked) {
		return false;
	}
	clicked = true;

	document.getElementById('clickANext').href = clickNext;
	document.getElementById('clickBNext').href = clickNext;
	if (document.getElementById('clickCNext')) {
		document.getElementById('clickCNext').href = clickNext;
	}

	if (id === "clickA" && answerA === "true") {
		document.getElementById('clickA').style.backgroundColor = 'green';
		setDisable();
		sleep(2000);
		return;
	} else if (id === "clickB" && answerB === "true") {
		document.getElementById('clickB').style.backgroundColor = 'green';
		setDisable();
		sleep(2000);
		return;
	} else if (id === "clickC" && answerC === "true") {
		document.getElementById('clickC').style.backgroundColor = 'green';
		setDisable();
		sleep(2000);
		return;
	} else {
		document.getElementById(id).style.backgroundColor = 'red';
	}

	if (answerA === "true") {
		document.getElementById('clickA').style.backgroundColor = 'green';
	} else if (answerB === "true") {
		document.getElementById('clickB').style.backgroundColor = 'green';
	} else {
		document.getElementById('clickC').style.backgroundColor = 'green';
	}
	setDisable();
	sleep(2000);
	// document.getElementById('clickANext').className = "btn btn-large
	// disabled";
	// document.getElementById('clickBNext').className = "btn btn-large
	// disabled";
	// if (document.getElementById('clickCNext')) {
	// document.getElementById('clickCNext').className = "btn btn-large
	// disabled";
	// }

	// sleep(2000);

}

function setDisable() {
	$('#clickANext').addClass('btn btn-large disabled');
	$('#clickBNext').addClass('btn btn-large disabled');
	if (document.getElementById('clickCNext')) {
		$('#clickCNext').addClass('btn btn-large disabled');
	}
}