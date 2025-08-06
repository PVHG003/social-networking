import {Card, CardContent, CardHeader} from "@/components/ui/card.tsx";
import {Input} from "@/components/ui/input.tsx";
import {Label} from "@/components/ui/label.tsx";
import {Link} from "react-router-dom";
import {Button} from "@/components/ui/button.tsx";

const AuthForm = ({mode}: { mode: 'login' | 'register' }) => {
    return (
        <div className={'flex flex-col items-center justify-center min-h-screen'}>
            <Card className={'w-full max-w-sm'}>
                <CardHeader className="text-center text-2xl font-bold">
                    {mode === 'login' ? 'Sign In' : 'Create Account'}
                </CardHeader>
                <CardContent>
                    <form className={'flex flex-col gap-4'}>
                        <div>
                            <Label>Email</Label>
                            <Input type={'email'} placeholder={'Enter Email'}></Input>
                        </div>

                        <div>
                            <Label>Password</Label>
                            <Input type={'password'} placeholder={'Enter Password'}></Input>
                        </div>

                        {mode === "register" && (
                            <div>
                                <Label>Confirm Password</Label>
                                <Input type={'password'} placeholder={'Enter Password'}></Input>
                            </div>
                        )}

                        <Button type="submit" className="w-full mt-2">
                            {mode === 'login' ? 'Login' : 'Register'}
                        </Button>

                        <div className="text-sm text-center mt-2">
                            {mode === 'login' ? (
                                <>
                                    Don’t have an account?{' '}
                                    <Link to="/register" className="text-blue-500 hover:underline">
                                        Register
                                    </Link>
                                </>
                            ) : (
                                <>
                                    Already have an account?{' '}
                                    <Link to="/login" className="text-blue-500 hover:underline">
                                        Sign In
                                    </Link>
                                </>
                            )}
                        </div>
                    </form>
                </CardContent>
            </Card>
        </div>
    )
}

export default AuthForm;