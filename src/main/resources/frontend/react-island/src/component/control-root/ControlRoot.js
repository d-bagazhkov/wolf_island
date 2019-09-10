import React from 'react';
import './ControlRoot.css';
import {ControlPanel, ConfigPanel} from "../";

export default class ControlRoot extends React.Component {

  render() {
    return (
        <div>
          <ControlPanel/>
          <ConfigPanel />
        </div>
    );
  }

}