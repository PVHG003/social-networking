import type { FunctionComponent } from "react";

interface FeedPageProps {}

const FeedPage: FunctionComponent<FeedPageProps> = () => {
  return (
    <div className="flex flex-col items-center justify-center h-full">
      <h1 className="text-3xl font-bold mb-4">Welcome to your Feed Page</h1>
    </div>
  );
};

export default FeedPage;
