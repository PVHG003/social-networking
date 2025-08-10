import type { FunctionComponent } from "react";
import PostCard from "./PostCard";

interface PostListProps {}

const posts = [
  {
    id: "1",
    username: "username",
    profilePicture: "https://placehold.co/400",
    content:
      "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
    postMedias: [
      "https://placehold.co/600x400",
      "https://placehold.co/600x400/red/blue",
      "https://placehold.co/600x400/white/black",
      "https://placehold.co/600x400/black/white",
      "https://placehold.co/600x400/orange/white?text=Hello\nWolrd",
    ],
    likeCounts: 10,
    commentCounts: 2,
    createdAt: "2025-08-10T03:04:37.389Z",
    updatedAt: "2025-08-10T03:04:37.389Z",
  },
  {
    id: "2",
    username: "username",
    profilePicture: "https://placehold.co/400",
    content:
      "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
    postMedias: [
      "https://placehold.co/600x400",
      "https://placehold.co/600x400/red/blue",
      "https://placehold.co/600x400/white/black",
      "https://placehold.co/600x400/black/white",
      "https://placehold.co/600x400/orange/white?text=Hello\nWolrd",
    ],
    likeCounts: 1000000,
    commentCounts: 200,
    createdAt: "2025-08-10T03:04:37.389Z",
    updatedAt: "2025-08-10T03:04:37.389Z",
  },
  {
    id: "3",
    username: "username",
    profilePicture: "https://placehold.co/400",
    content:
      "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
    postMedias: [
      "https://placehold.co/600x400",
      "https://placehold.co/600x400/red/blue",
      "https://placehold.co/600x400/white/black",
      "https://placehold.co/600x400/black/white",
      "https://placehold.co/600x400/orange/white?text=Hello\nWolrd",
    ],
    likeCounts: 0,
    commentCounts: 1000,
    createdAt: "2025-08-10T03:04:37.389Z",
    updatedAt: "2025-08-10T03:04:37.389Z",
  },
  {
    id: "4",
    username: "username",
    profilePicture: "https://placehold.co/400",
    content:
      "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
    postMedias: [
      "https://placehold.co/600x400",
      "https://placehold.co/600x400/red/blue",
      "https://placehold.co/600x400/white/black",
      "https://placehold.co/600x400/black/white",
      "https://placehold.co/600x400/orange/white?text=Hello\nWolrd",
    ],
    likeCounts: 10,
    commentCounts: 20,
    createdAt: "2025-08-10T03:04:37.389Z",
    updatedAt: "2025-08-10T03:04:37.389Z",
  },
  {
    id: "5",
    username: "username",
    profilePicture: "https://placehold.co/400",
    content:
      "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
    postMedias: [
      "https://placehold.co/1500x500/orange/white?text=Hello\nWolrd",
      "https://placehold.co/600x400",
      "https://placehold.co/600x400/red/blue",
      "https://placehold.co/600x400/white/black",
      "https://placehold.co/600x400/black/white",
      "https://placehold.co/600x400/orange/white?text=Hello\nWolrd",
      "https://placehold.co/600x400",
      "https://placehold.co/600x400/red/blue",
      "https://placehold.co/600x400/white/black",
      "https://placehold.co/600x400/black/white",
    ],
    likeCounts: 1000,
    commentCounts: 100,
    createdAt: "2025-08-10T03:04:37.389Z",
    updatedAt: "2025-08-10T03:04:37.389Z",
  },
];

const PostList: FunctionComponent<PostListProps> = () => {
  return (
    <div>
      {posts.map((post) => (
        <div key={post.id}>
          <PostCard
            key={post.id}
            id={post.id}
            username={post.username}
            handleName={post.username} // if you have a separate handleName, use it here
            profilePicture={post.profilePicture}
            content={post.content}
            postMedias={post.postMedias}
            likeCounts={post.likeCounts}
            commentCounts={post.commentCounts}
          />
        </div>
      ))}
    </div>
  );
};

export default PostList;
