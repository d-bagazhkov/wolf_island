import React from 'react';
import PropTypes from 'prop-types';
import './AnimaInput.css'

class AnimaInput extends React.Component {

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
            <div className="AnimaInput">
                <label
                    style={{bottom: this.state.isPlaceholder ? 0 : this.state.inputHeight}}
                    onClick={this.labelHandleClick}
                >
                    {this.state.isPlaceholder ? this.state.placeholder : this.props.label}
                </label>
                <input
                    onBlur={this.inputHandleBlur}
                    onFocus={this.labelHandleClick}
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

    inputHandleBlur = ({target}) => {
        if (target.value) {
            this.setState({isPlaceholder: false})
        } else {
            this.setState({isPlaceholder: true})
        }
        this.props.update && this.props.update(target.value);
    }
}

AnimaInput.propTypes = {
    label: PropTypes.string,
    value: PropTypes.oneOfType([
        PropTypes.string,
        PropTypes.number
    ])
};

export default AnimaInput;