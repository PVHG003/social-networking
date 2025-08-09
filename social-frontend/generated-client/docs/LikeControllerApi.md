# LikeControllerApi

All URIs are relative to *http://localhost:8080*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**getLikes**](#getlikes) | **GET** /api/posts/{postId}/likes | |
|[**likePost**](#likepost) | **POST** /api/posts/{postId}/likes | |
|[**unlikePost**](#unlikepost) | **DELETE** /api/posts/{postId}/likes | |

# **getLikes**
> ApiPagedResponseLikeResponse getLikes()


### Example

```typescript
import {
    LikeControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new LikeControllerApi(configuration);

let postId: string; // (default to undefined)

const { status, data } = await apiInstance.getLikes(
    postId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **postId** | [**string**] |  | defaults to undefined|


### Return type

**ApiPagedResponseLikeResponse**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **likePost**
> ApiResponseVoid likePost()


### Example

```typescript
import {
    LikeControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new LikeControllerApi(configuration);

let postId: string; // (default to undefined)

const { status, data } = await apiInstance.likePost(
    postId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **postId** | [**string**] |  | defaults to undefined|


### Return type

**ApiResponseVoid**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **unlikePost**
> ApiResponseVoid unlikePost()


### Example

```typescript
import {
    LikeControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new LikeControllerApi(configuration);

let postId: string; // (default to undefined)

const { status, data } = await apiInstance.unlikePost(
    postId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **postId** | [**string**] |  | defaults to undefined|


### Return type

**ApiResponseVoid**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

