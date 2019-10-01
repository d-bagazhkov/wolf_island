import React from 'react';
import "./ToggleSwitch.css";

export default class ToggleSwitch extends React.Component {

  constructor(props) {
    super(props);
    let {offValue} = this.props;
    this.switchInstance = null;
    this.state = {value: offValue, toggleChecked: false}
  }


  render() {
    if (this.switchInstance) {
      if (this.state.toggleChecked)
        this.switchInstance.classList.add("checked");
      else
        this.switchInstance.classList.remove("checked");
    }
    return (
        <div className="ToggleSwitch">
          <label
              className="switch-label"
              onClick={this.toggleOnChange}
          >
            <span className="switch-button" ref={instance => this.switchInstance = instance}/>
          </label>
          <label className="info">{this.state.value}</label>
        </div>
    );
  }

  toggleOnChange = (e) => {
    let {offValue, onValue, onChange} = this.props;
    let curCheck = !this.state.toggleChecked;
    onChange && onChange(curCheck);
    this.setState({
      toggleChecked: curCheck,
      value: curCheck ? onValue : offValue
    });
  }
}