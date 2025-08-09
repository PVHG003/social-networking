import "./App.css";
import {
  Configuration,
  PostControllerApi,
  type ApiPagedResponsePostResponse,
} from "../generated-client";
import { useEffect, useState } from "react";

function App() {
  const [posts, setPosts] = useState<ApiPagedResponsePostResponse>(
    {} as ApiPagedResponsePostResponse
  );

  useEffect(() => {
    const api = new PostControllerApi(
      new Configuration({
        basePath: "http://localhost:8080",
        baseOptions: {
          withCredentials: true,
        },
      })
    );

    api
      .explore(1, 10)
      .then((response) => {
        if (response.data.success) {
          setPosts(response.data);
        } else {
          console.error("No posts found");
        }
      })
      .catch((error) => {
        console.error("Error fetching posts:", error);
      });
  }, []);

  return (
    <ul>
      {posts.data?.map((post) => (
        <li key={post.id}>{post.content}</li>
      ))}
    </ul>
  );
}

export default App;
