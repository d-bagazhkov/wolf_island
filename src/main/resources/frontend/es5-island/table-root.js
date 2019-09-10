'use strict';

const elementRootTable = document.getElementById("table-root");
const props = {
  cellSize: +elementRootTable.dataset.sizeCell
};

const Entity = function (json) {
  this.x = json.x;
  this.y = json.y;
  this.value = json.value;
};
Entity.of = (arrObj) => arrObj.map(e => new Entity({x: e.position.x, y: e.position.y, value: e.thing.value}));

const Cell = function (entity) {
  this.element = document.createElement("div");
  this.render = entity => {
    this.element.innerHTML = entity.value;
    this.entity = entity;
  };
  this.element.dataset.index = entity.x;
  this.element.classList.add("cell");
  this.element.style.width = props.cellSize + 'px';
  this.element.style.height = props.cellSize + 'px';
};

const Row = function (entity) {
  this.listCell = [];
  this.element = document.createElement("div");
  this.element.dataset.index = entity.y;
  this.element.style.height = props.cellSize + 'px';
  this.element.classList.add("row");
  this.add = cell => {
    this.listCell.push(cell);
    this.element.append(cell.element);
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
      let cell = row.listCell[entity.x];
      if (!cell) {
        cell = new Cell(entity);
        row.add(cell);
      }
      cell.render(entity);
    }
  };
};