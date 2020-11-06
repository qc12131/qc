/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qccloud.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @Author: 青菜
 * @Date: 2019/10/22 下午4:56
 * @Description: rest template 配置类
 * @Version 1.0
 */
@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
		// 设置字符集
		for (HttpMessageConverter<?> httpMessageConverter : list) {
			if(httpMessageConverter instanceof StringHttpMessageConverter) {
				((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(Charset.forName("utf-8"));
				break;
			}
		}
		return restTemplate;
	}

	@Bean("urlConnection")
	public RestTemplate urlConnectionRestTemplate(){
		RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
		return restTemplate;
	}

	@Bean("httpClient")
	public RestTemplate httpClientRestTemplate(){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		return restTemplate;
	}

    /*@Bean("OKHttp3")
    public RestTemplate OKHttp3RestTemplate(){
        RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
        return restTemplate;
    }*/
}
