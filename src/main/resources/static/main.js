'use strict';

// let root = document.querySelector(APP_CONFIG.ISLAND_CONFIG.ISLAND_SELECTOR);
// root = new RootTable({element: root, config: APP_CONFIG.ISLAND_CONFIG});
const root = new RootTable({
  element: document.querySelector(APP_CONFIG.ISLAND_CONFIG.ISLAND_SELECTOR),
  config: APP_CONFIG.ISLAND_CONFIG
});

let control = new ControlGroup({
  element: document.querySelector(APP_CONFIG.CONTROL_CONFIG.CONTROL_SELECTOR),
  config: APP_CONFIG.CONTROL_CONFIG
});

root.getCeil(1, 1).set("H");
root.getCeil(2, 1).set("e");
root.getCeil(3, 1).set("l");
root.getCeil(4, 1).set("l");
root.getCeil(5, 1).set("o");

root.getCeil(3, 3).set("W");
root.getCeil(4, 3).set("o");
root.getCeil(5, 3).set("r");
root.getCeil(6, 3).set("l");
root.getCeil(7, 3).set("d");

let triggerId;
const trigger = (q) => console.log(q);
control.addResetCallback(event => {

});
control.addStartCallback(event => {
  triggerId = setInterval(() => trigger(event), +control.startButton.dataset.frequency);
});
control.addRadioCallback(event => {
  if (event.target.value === 'Off') {
    clearInterval(triggerId);
    control.startButton.disabled = true;
    control.resetButton.disabled = false;
  } else {
    control.resetButton.disabled = true;
    control.startButton.disabled = false;
  }
  control.startButton.dataset.frequency = event.target.value;
});

control.build();