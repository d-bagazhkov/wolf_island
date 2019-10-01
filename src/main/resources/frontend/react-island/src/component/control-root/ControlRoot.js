import React from 'react';
import './ControlRoot.css';
import {ControlPanel, StatisticPanel} from "../";

export default class ControlRoot extends React.Component {

  render() {
    return (
        <div className="ControlRoot">
          <ControlPanel
              defaultConfig={this.props.defaultConfig}
              callback={this.configCallback}
              intervalCallback={this.props.intervalCallback}
          />
          <StatisticPanel
              values={{"key": "value","key2": "value","key3": "value","key4": "value","key5": "value","key6": "value"}}
          />
        </div>
    );
  }

}