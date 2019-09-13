import React from 'react';
import './App.css';
import {ControlRoot, IslandRoot} from "../";

export default class App extends React.Component {

  defaultConfig = {
    cellSize: "3rem",
    countColumns: 10,
    countRows: 10
  };

  state = {
    interval: 0,
    config: {...this.defaultConfig}
  };

  intervalCallback = (interval) => {
    console.log(interval);
    this.setState({
      interval
    });
  };

  configCallback = (config) => {
    this.setState({config});
  };

  render() {
    return (
        <div className="App">
          <ControlRoot
              intervalCallback={this.intervalCallback}
              configCallback={this.configCallback}
              defaultConfig={this.defaultConfig}
              statsUrl={"http://localhost:8008/socket/stats/"}
          />
          <IslandRoot
              interval={this.state.interval}
              config={this.state.config}
              islandUrl={"http://localhost:8008/socket/handle/"}
          />
        </div>
    );
  }
}

