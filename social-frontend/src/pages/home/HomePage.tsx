import type { FunctionComponent } from "react";

interface HomePageProps {}

const HomePage: FunctionComponent<HomePageProps> = () => {
  return (
    <div className="flex flex-col items-center justify-center h-full">
      <h1 className="text-3xl font-bold mb-4">Welcome to the Home Page</h1>
    </div>
  );
};

export default HomePage;
