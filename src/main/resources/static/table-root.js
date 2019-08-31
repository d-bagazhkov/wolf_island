'use strict';

const elementRootTable = document.getElementById("table-root");
const props = {
  ceilSize: +elementRootTable.dataset.sizeCeil
};

const Entity = function (json) {
  this.x = json.x;
  this.y = json.y;
  this.value = json.value;
};
Entity.of = (arrObj) => arrObj.map(e => new Entity({x: e.position.x, y: e.position.y, value: e.thing.value}));

const Ceil = function (entity) {
  this.element = document.createElement("div");
  this.render = entity => {
    this.element.innerHTML = entity.value;
    this.entity = entity;
  };
  this.element.dataset.index = entity.x;
  this.element.classList.add("ceil");
  this.element.style.width = props.ceilSize + 'px';
  this.element.style.height = props.ceilSize + 'px';
};

const Row = function (entity) {
  this.listCeil = [];
  this.element = document.createElement("div");
  this.element.dataset.index = entity.y;
  this.entity = entity;
  this.element.style.height = props.ceilSize + 'px';
  this.element.classList.add("row");
  this.add = ceil => {
    this.listCeil.push(ceil);
    this.element.append(ceil.element);
  };
};

const RootTable = function (element) {
  this.rows = [];
  this.element = element;
  this.add = row => {
    this.rows.push(row);
    this.element.append(row.element);
    return row
  };
  this.render = arrEntities => {
    for (let entity of arrEntities) {
      let row = this.rows[entity.y];
      if (!row) {
        row = new Row(entity);
        this.add(row);
      }
      let ceil = row.listCeil[entity.x];
      if (!ceil) {
        ceil = new Ceil(entity);
        row.add(ceil);
      }
      ceil.render(entity);
    }
  };
};