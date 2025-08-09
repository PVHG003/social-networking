# PostControllerApi

All URIs are relative to *http://localhost:8080*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**createPost**](#createpost) | **POST** /api/posts | |
|[**deletePost**](#deletepost) | **DELETE** /api/posts/{postId} | |
|[**explore**](#explore) | **GET** /api/posts/explore | |
|[**getPost**](#getpost) | **GET** /api/posts/{postId} | |
|[**updatePost**](#updatepost) | **PUT** /api/posts/{postId} | |

# **createPost**
> ApiResponsePostResponse createPost()


### Example

```typescript
import {
    PostControllerApi,
    Configuration,
    PostRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new PostControllerApi(configuration);

let data: PostRequest; // (optional) (default to undefined)
let files: Array<File>; // (optional) (default to undefined)

const { status, data } = await apiInstance.createPost(
    data,
    files
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **data** | **PostRequest** |  | (optional) defaults to undefined|
| **files** | **Array&lt;File&gt;** |  | (optional) defaults to undefined|


### Return type

**ApiResponsePostResponse**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **deletePost**
> ApiResponseVoid deletePost()


### Example

```typescript
import {
    PostControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new PostControllerApi(configuration);

let postId: string; // (default to undefined)

const { status, data } = await apiInstance.deletePost(
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

# **explore**
> ApiPagedResponsePostResponse explore()


### Example

```typescript
import {
    PostControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new PostControllerApi(configuration);

let page: number; // (optional) (default to 0)
let size: number; // (optional) (default to 10)

const { status, data } = await apiInstance.explore(
    page,
    size
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **page** | [**number**] |  | (optional) defaults to 0|
| **size** | [**number**] |  | (optional) defaults to 10|


### Return type

**ApiPagedResponsePostResponse**

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

# **getPost**
> ApiResponsePostResponse getPost()


### Example

```typescript
import {
    PostControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new PostControllerApi(configuration);

let postId: string; // (default to undefined)

const { status, data } = await apiInstance.getPost(
    postId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **postId** | [**string**] |  | defaults to undefined|


### Return type

**ApiResponsePostResponse**

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

# **updatePost**
> ApiResponsePostResponse updatePost(postRequest)


### Example

```typescript
import {
    PostControllerApi,
    Configuration,
    PostRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new PostControllerApi(configuration);

let postId: string; // (default to undefined)
let postRequest: PostRequest; //

const { status, data } = await apiInstance.updatePost(
    postId,
    postRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **postRequest** | **PostRequest**|  | |
| **postId** | [**string**] |  | defaults to undefined|


### Return type

**ApiResponsePostResponse**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

