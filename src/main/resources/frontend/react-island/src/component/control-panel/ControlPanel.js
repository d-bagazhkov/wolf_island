import React from 'react';
import {InputRange, ToggleSwitch, Button} from "../";
import './ControlPanel.css';

export default class ControlPanel extends React.Component {

  render() {
    return (
        <div className="ControlPanel">
          <InputRange/>
          <Button value="Reset" />
          <ToggleSwitch/>
        </div>
    );
  }

}