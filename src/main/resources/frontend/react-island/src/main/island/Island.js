import React from 'react';
import Cell from "./cell/Cell.js";
import './Island.css';

export default class Island extends React.Component {

  constructor(props, context) {
    super(props, context);
    const source = new EventSource(props.islandUrl);
    source.onmessage = (event) => this.updateStateCells(JSON.parse(event.data));
    this.state = {
      cells: []
    }
  }

  render() {
    return (
        <div className="Island">
          {
            this.state.cells.map(row => {
              return (<div className="Row">
                {
                  row.map(cell => <Cell size={this.props.cellSize} value={cell.value}/>)
                }
              </div>);
            })
          }
        </div>
    );
  }

  updateStateCells(message) {
    let result = [];
    message.forEach(m => {
      let thing = new Thing(m);
      if (!result[thing.y])
        result[thing.y] = [];
      result[thing.y][thing.x] = thing;
    });
    result.sort(this.sortTable);
    this.setState({cells: result})
  }

  sortTable = (second, first) => second.x * (second.y + 1) - first.x * (first.y + 1);

}
const Thing = function (json) {
  this.value = json.thing.value;
  this.x = json.position.x;
  this.y = json.position.y;
};
