import {Route, Routes} from "react-router-dom";
import LoginPage from "@/pages/auth/LoginPage.tsx";
import RegisterPage from "@/pages/auth/RegisterPage.tsx";

function App() {
    return (
        <Routes>
            <Route path={'/login'} element={<LoginPage/>}/>
            <Route path={'/register'} element={<RegisterPage/>}/>
        </Routes>
    )
}

export default App