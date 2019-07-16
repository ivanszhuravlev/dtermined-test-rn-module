import React from 'react'
import { requireNativeComponent } from "react-native"

const DterminedView = requireNativeComponent('DterminedView')

export const DterminedButton = ({ text, ...props }) => {

    const clickCallback = (event) => {
        if (!props.onClick)
            return
        props.onClick(event.nativeEvent)
    }

    return (
        <DterminedView 
            style={{width: '100%', height: 50}}
            text={text}
            onClick={clickCallback}
        />
    )
}
