import React from 'react';
import './App.css';
import {ControlRoot, IslandRoot} from "../";

export default class App extends React.Component {

  state = {
    interval: "Off"
  };

  intervalCallback(interval) {
    this.setState({
      interval
    })
  };

  render() {
    return (
        <div className="App">
          <ControlRoot
              callback={this.intervalCallback.bind(this)}
              interval={this.state.interval}
              statsUrl={"http://localhost:8008/socket/stats/"}
          />
          <IslandRoot
              cellSize={50}
              interval={this.state.interval}
              islandUrl={"http://localhost:8008/socket/handle/"}
          />
        </div>
    );
  }
}

