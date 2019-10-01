import React from 'react';
import './AnimaButton.css';

const AnimaButton = ({onClick, value}) => (
    <input className={"AnimaButton"} onClick={onClick || (() =>{})} type="button" value={value || ""} />
);

export default AnimaButton;