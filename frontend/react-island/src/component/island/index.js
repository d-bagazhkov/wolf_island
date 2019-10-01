import React from 'react';
import {connect} from "react-redux";
import './Island.css'

const Island = ({table, size}) => (
    <div className="Island">
        {Object.entries(table).sort(([a, b]) => +a[0] - +b[0]).map(([numRow, row]) =>
            <div key={numRow} className={"row"}>
                {Object.entries(row).map(([numCol, cell]) =>
                    <div key={numCol} style={{width: size, height: size}} className={"cell"}>
                        {cell}
                    </div>
                )}
            </div>
        )}
    </div>
);

const mapStateToProps = state => ({
    table: state.island,
    size: state.config.cellSize
});

const mapDispatchToProps = null;

export default connect(mapStateToProps, mapDispatchToProps)(Island);