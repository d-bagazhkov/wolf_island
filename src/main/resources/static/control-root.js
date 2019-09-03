'use strict';

const controlRoot = document.getElementById("control-root");

const sectionRangeInput = controlRoot.querySelector("section.range-input");
const rangeInput = sectionRangeInput.querySelector("input[type=range]");
const rangeValue = sectionRangeInput.querySelector("label.info.value");
rangeValue.innerHTML = "Off";

const resetSection = controlRoot.querySelector("section.reset-button");
const resetButton = resetSection.querySelector("button.reset");
const buttonAlarm = resetSection.querySelector("label.info.alarm");

const toggleSwitchSection = controlRoot.querySelector("section.toggle-switch");
const toggleCheckbox = toggleSwitchSection.querySelector("input[type=checkbox]");
const toggleAlarm = toggleSwitchSection.querySelector("label.info.alarm");
const toggleValue = toggleSwitchSection.querySelector("label.info.value");
toggleValue.innerHTML = "Polling";


let switchToSocket = () => {};
let switchToPolling = () => {};
let startQuerying = (value) => {};
let stopQuerying = () => {};
let resetApp = () => {};

rangeInput.addEventListener("input", function (event) {
  let value = +event.target.value;
  if (value) {
    value = value / 1000 + " sec";
  } else {
    buttonAlarm.innerHTML = '';
    toggleAlarm.innerHTML = '';
    value = "Off";
  }
  rangeValue.innerHTML = value;
});

rangeInput.addEventListener("change", function (event) {
  let value = +event.target.value;
  if (value) {
    startQuerying(value);
  } else {
    stopQuerying();
  }
});

resetButton.addEventListener("click", function () {
  if (+rangeInput.value) {
    buttonAlarm.innerHTML = "Switch to off range";
    console.log("Нет возможности перезапустить программу, не останавливая запросы. Перетащите ползунок на Off");
    return;
  }
  resetApp();
});

toggleCheckbox.addEventListener("click", function (event) {
  if (+rangeInput.value) {
    toggleAlarm.innerHTML = "Switch to off range";
    console.log("Нет возможности сменить тип запросов, не останавливая их. Перетащите ползунок на Off");
    event.target.checked = !event.target.checked;
    return;
  }

  if (event.target.checked) {
    toggleValue.innerHTML = "Socket";
    switchToSocket();
  } else {
    toggleValue.innerHTML = "Polling";
    switchToPolling();
  }
});