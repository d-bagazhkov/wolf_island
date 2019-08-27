'use strict';

let rootElement = document.querySelector(APP_CONFIG.ISLAND_SELECTOR);
const root = new RootTable({
  element: rootElement,
  config: {
    COUNT_VERTICAL_CEIL: rootElement.dataset.countCeilY,
    COUNT_HORIZONTAL_CEIL: rootElement.dataset.countCeilX,
    SIZE_CEIL: rootElement.dataset.sizeCeil
  }
});

let control = new ControlGroup({
  element: document.querySelector(APP_CONFIG.CONTROL_SELECTOR)
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

const getPositions = () => {
  let pos = root.getAll().map(e => {
    return {
      position: {
        x: e.x,
        y: e.y
      },
      name: e.name
    }
  });
  return pos;
};

let triggerId;
const trigger = (q) => {
  fetch("/handle", {
    method: 'POST',
    body: JSON.stringify(getPositions()),
    headers: new Headers({
      'Content-Type': 'application/json'
    }),
  }).then(response => response.json())
      .then(j => root.saveAll(j));
};
control.addResetCallback(event => {
  fetch("/reset")
      .then(response => response.json())
      .then(response => root.saveAll(response))
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