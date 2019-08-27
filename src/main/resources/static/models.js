'use strict';

var Ceil = function ({coordinateX, config}) {
  this.value = null;
  const createCeil = function (horizontal_index) {
    let ceil = document.createElement("div");
    ceil.style.width = config.SIZE_CEIL + 'px';
    ceil.style.height = config.SIZE_CEIL + 'px';
    ceil.classList.add('ceil');
    return ceil;
  };
  this.element = createCeil(coordinateX);
  this.element.dataset.index = coordinateX;
  this.index = coordinateX;
  this.toString = this.element.toString;
  this.set = (text) => {this.element.innerHTML = text; this.value = text};
  this.clear = () => this.set("");

};

var Row = function ({coordinateY, config}) {
  const createRow = function (vertical_index) {
    let row = document.createElement("div");
    row.style.height = config.SIZE_CEIL + 'px';
    row.dataset.index = vertical_index;
    row.classList.add('row');
    return row;
  };
  let ceils = [];
  this.element = createRow(coordinateY);
  this.index = coordinateY;
  this.toString = this.element.toString;
  this.add = (ceil) => {
    if (ceil instanceof Ceil) {
      ceils.push(ceil);
      this.element.append(ceil.element)
    }
  };
  this.get = (index) => ceils.filter(c => c.index === index)[0];
  this.clear = () => this.cells.forEach(c => c.clear());
  this.addCeil = x => {
    let ceil = new Ceil({coordinateX: x, config});
    this.add(ceil);
    return ceil;
  }
};

var RootTable = function ({element, config}) {
  let rows = [];
  this.element = element;
  this.element.classList.add("grid-root");
  this.getCeil = (x, y) => this.getRow(y).get(x);
  this.getRow = y => rows.filter(r => r.index === y)[0];
  this.add = row => {
    if (row instanceof Row) {
      rows.push(row);
      this.element.append(row.element)
    }
  };
  this.addRow = y => {
    let row = new Row({coordinateY: y, config});
    this.add(row);
    return row;
  };
  this.getAll = () => {
    let result = [];
    for (let y = 0; y < config.COUNT_VERTICAL_CEIL; y++) {
      let row = this.getRow(y);
      for (let x = 0; x < config.COUNT_HORIZONTAL_CEIL; x++) {
        result.push({
          x, y, name: row.get(x).value
        });
      }
    }
    return result;
  };
  this.saveAll = arr => {
    for (let e of arr) {
      this.getRow(e.position.y).get(e.position.x).set(e.name || "")
    }
  };
  for (let y = 0; y < config.COUNT_VERTICAL_CEIL; y++) {
    let row = this.addRow(y);
    for (let x = 0; x < config.COUNT_HORIZONTAL_CEIL; x++) {
      row.addCeil(x);
    }
  }
};

var ControlGroup = function ({element, config}) {
  let startCallback;
  let resetCallback;
  let radioCallback;
  this.element = element;
  this.controlGroupBuilder = (element) => {
    const createSection = (className) => {
      const section = document.createElement("section");
      section.classList.add(className);
      element.append(section);
      return section
    };
    const createButton = (text) => {
      let button = document.createElement("button");
      button.name = text.toLocaleLowerCase();
      button.textContent = text;
      return button
    };
    const createRadio = (name, value, checked) => {
      let radio = document.createElement("input");
      radio.type = 'radio';
      radio.name = name;
      radio.value = value;
      if (checked)
        radio.checked = checked;
      let p = document.createElement("p");
      p.append(radio, value);
      return p;
    };
    let section = createSection("button");
    this.startButton = createButton("Start");
    this.startButton.disabled = true;
    this.startButton.addEventListener('click', startCallback);
    section.append(this.startButton);
    this.radioSection = createSection("radio");
    this.radioSection.append(createRadio('frequency', 'Off', true));
    this.radioSection.append(createRadio('frequency', '1000'));
    this.radioSection.append(createRadio('frequency', '2000'));
    this.radioSection.append(createRadio('frequency', '5000'));
    this.radioSection.append(createRadio('frequency', '10000'));
    this.radioSection.addEventListener('change', radioCallback);
    section = createSection("button");
    this.resetButton = createButton("Reset");
    this.resetButton.addEventListener('click', resetCallback);
    section.append(this.resetButton);
  };
  this.build = () => this.controlGroupBuilder(element);
  this.addStartCallback = callback => startCallback = callback;
  this.addResetCallback = callback => resetCallback = callback;
  this.addRadioCallback = callback => radioCallback = callback;
};

var Entity = (e) => {
  this.name = e.name;
  this.x = e.position.x;
  this.y = e.position.y;
};