import React, { FC } from 'react'
import { RouterProvider, createBrowserRouter, redirect, Outlet } from 'react-router-dom'
import MainPage from '../components/MainPage'
import CreateBattlePage from '../components/CreateBattlePage'
import Header from '../components/Header'

const Layout: FC = () => (
  <>
    <Header />
    <Outlet />
  </>
)

const router = createBrowserRouter([
  {
    element: <Layout/>,
    children: [
      {
        path: '/',
        action: args => {
          redirect('/battles')
        }
      },
      {
        path: '/battles',
        element: <MainPage/>
      },
      {
        path: '/battles/create',
        element: <CreateBattlePage/>
      }]
  }
])

const Router: FC = () => {
  return (
    <RouterProvider router={router}/>
  )
}

export default Router
