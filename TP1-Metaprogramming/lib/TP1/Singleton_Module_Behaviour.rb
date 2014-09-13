module TP

  module Singleton_Module_Accessors

    attr_accessor :singleton_module

    def singleton_module
      if !@singleton_module then
        @singleton_module = Module.new
        self.extend @singleton_module
      end
      @singleton_module
    end

    def define_singleton_module_method(selector, block)
      self.singleton_module.send(:define_method, selector, block)
    end

    def instance_module_variable_define(selector)
      self.singleton_module.module_eval{ attr_accessor selector }
    end

  end

  module Singleton_Module

    include Singleton_Module_Accessors

    # If the module is included by a class, it defines behaviour for every new instance AND for the class instance itself
    def self.included(including_class)
      including_class.singleton_class.include Singleton_Module_Accessors
    end

  end

end