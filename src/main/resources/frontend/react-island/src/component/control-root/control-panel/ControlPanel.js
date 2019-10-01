import React from 'react';
import {InputRange, ToggleSwitch, Button, Input} from "../../";
import './ControlPanel.css';

export default class ControlPanel extends React.Component {

  render() {
    return (
        <div className="ControlPanel">
          <InputRange onChange={this.props.intervalCallback}/>
          <Button value="Reset" />
          <ToggleSwitch offValue="Polling" onValue="Socket" onChange={(c) => console.log(c)}/>
          <Input label="Cell size" value={this.props.defaultConfig.cellSize} />
          <Input label="Count rows" value={this.props.defaultConfig.countRows} />
          <Input label="Count columns" value={this.props.defaultConfig.countColumns} />
          <Button />
        </div>
    );
  }

}