import React from 'react';
import { Spin } from 'antd';
import { SmileOutlined } from '@ant-design/icons';

export default function LoadingIndicator(props) {
    const antIcon = <SmileOutlined type="loading-3-quarters" style={{ fontSize: 30 }} spin />;
    return (
        <Spin indicator={antIcon} style = {{display: 'block', textAlign: 'center', marginTop: 30}} />
    );
}
