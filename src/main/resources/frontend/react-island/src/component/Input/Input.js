import React from 'react';
import './Input.css'

export default class Input extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      inputHeight: '1.3rem',
      placeholder: props.label + ": " + this.props.value,
      isPlaceholder: true
    };
  }


  render() {
    return (
        <div className="Input">
          <label
              style={{bottom: this.state.isPlaceholder ? 0 : this.state.inputHeight}}
              onClick={this.labelHandleClick}
          >
            {this.state.isPlaceholder ? this.state.placeholder : this.props.label}
          </label>
          <input
              onBlur={this.inputHandleBlur}
              ref={"input"}
              height={this.state.inputHeight}
              type="text"/>
        </div>
    );
  }

  labelHandleClick = () => {
    this.refs.input.focus();
    this.setState({isPlaceholder: false})
  };

  inputHandleFocus = () => {
    this.setState({isPlaceholder: false})
  };

  inputHandleBlur = ({target}) => {
    if (target.value) {
      this.setState({isPlaceholder: false})
    } else {
      this.setState({isPlaceholder: true})
    }
  }
}