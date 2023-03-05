import React, { FC } from 'react'

import './App.css'
import AppRouter from './general/AppRouter'
import { CssBaseline } from '@mui/material'

const App: FC = () => {
  return (
    <>
      <CssBaseline />
      <div className="App">
        <AppRouter/>
      </div>
    </>
  )
}

export default App
