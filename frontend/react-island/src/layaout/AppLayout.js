import React from 'react';
import './AppLayout.css';

const AppLayout = (prop) => (
    <div className={"AppLayout"}>
        {prop.children}
    </div>
);

export default AppLayout;
